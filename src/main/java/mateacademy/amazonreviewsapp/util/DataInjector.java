package mateacademy.amazonreviewsapp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import mateacademy.amazonreviewsapp.entity.product.Product;
import mateacademy.amazonreviewsapp.entity.product.service.ProductService;
import mateacademy.amazonreviewsapp.entity.review.Review;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewMapper;
import mateacademy.amazonreviewsapp.entity.review.dto.ReviewSourceDto;
import mateacademy.amazonreviewsapp.entity.review.service.ReviewService;
import mateacademy.amazonreviewsapp.entity.role.Role;
import mateacademy.amazonreviewsapp.entity.role.service.RoleService;
import mateacademy.amazonreviewsapp.entity.user.User;
import mateacademy.amazonreviewsapp.entity.user.service.UserService;
import mateacademy.amazonreviewsapp.entity.word.dto.WordMapper;
import mateacademy.amazonreviewsapp.entity.word.service.WordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInjector {
    private static final int CLEAR_THRESHOLD = 50_000;
    private final LineParser<ReviewSourceDto> lineParser;
    private final RoleService roleService;
    private final UserService userService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final WordService wordService;
    private final PasswordEncoder passwordEncoder;
    private final ReviewMapper reviewMapper;
    private final WordMapper wordMapper;

    public DataInjector(LineParser<ReviewSourceDto> lineParser, RoleService roleService,
                        UserService userService, ProductService productService,
                        ReviewService reviewService, WordService wordService,
                        PasswordEncoder passwordEncoder, ReviewMapper reviewMapper,
                        WordMapper wordMapper) {
        this.lineParser = lineParser;
        this.roleService = roleService;
        this.userService = userService;
        this.productService = productService;
        this.reviewService = reviewService;
        this.wordService = wordService;
        this.passwordEncoder = passwordEncoder;
        this.reviewMapper = reviewMapper;
        this.wordMapper = wordMapper;
    }

    @PostConstruct
    public void init() {
        injectRoles();
        injectAdmin();
    }

    private void injectRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setName(Role.RoleName.ADMIN);
        roleService.save(roleAdmin);
        Role roleUser = new Role();
        roleUser.setName(Role.RoleName.USER);
        roleService.save(roleUser);
    }

    private void injectAdmin() {
        User admin = new User();
        admin.setProfileName("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Set.of(roleService.getByName("ADMIN")));
        userService.save(admin);
    }

    public void injectDataFromFile(List<String> lines) {
        Set<Product> products = new HashSet<>();
        Set<User> users = new HashSet<>();
        List<Review> reviews = new ArrayList<>();
        Map<String, Integer> words = new HashMap<>();
        lines.stream()
                .skip(lineParser.getOffset())
                .forEach(line -> addEntitiesToLists(line, products, users, reviews, words));
        saveEntities(products, users, reviews);
        saveWords(words);
    }

    private void addEntitiesToLists(String line, Set<Product> products, Set<User> users,
                                    List<Review> reviews, Map<String, Integer> words) {
        ReviewSourceDto reviewSourceDto = lineParser.parseLine(line);
        Product product = reviewMapper.getProduct(reviewSourceDto);
        User user = reviewMapper.getUser(reviewSourceDto);
        Review review = reviewMapper.getReview(reviewSourceDto, product, user);
        products.add(product);
        users.add(user);
        reviews.add(review);
        for (String word : review.getText().split("\\W+")) {
            word = word.toLowerCase();
            words.put(word, words.getOrDefault(word, 0) + 1);
        }
        checkReviewsListSize(products, users, reviews);
    }

    private void checkReviewsListSize(Set<Product> products, Set<User> users,
                                      List<Review> reviews) {
        if (reviews.size() >= CLEAR_THRESHOLD) {
            saveEntities(products, users, reviews);
            products.clear();
            users.clear();
            reviews.clear();
        }
    }

    @Transactional
    void saveEntities(Set<Product> products, Set<User> users, List<Review> reviews) {
        productService.saveAll(products);
        userService.saveAll(users);
        reviewService.saveAll(reviews);
    }

    @Transactional
    void saveWords(Map<String, Integer> words) {
        wordService.saveAll(words.entrySet()
                .stream()
                .map(wordMapper::getWord)
                .collect(Collectors.toList()));
    }
}
