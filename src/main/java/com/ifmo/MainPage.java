package com.ifmo;

import com.ifmo.db.Point;
import com.ifmo.input.PointsCredentials;
import com.ifmo.input.PointChecker;
import com.ifmo.dbInterface.UserRepository;
import com.ifmo.input.PointService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MainPage {
    private final PointService pointService;
    private final PointChecker pointChecker;
    private final UserRepository userRepository;

    public MainPage(PointService pointService, PointChecker pointChecker, UserRepository userRepository) {
        this.pointService = pointService;
        this.pointChecker = pointChecker;
        this.userRepository = userRepository;
    }


    @GetMapping("/point")
    public void getPoint(){}

    @InitBinder
    public void initBinder(@NotNull WebDataBinder webDataBinder) {
        webDataBinder.addValidators(pointChecker);
    }

    @PostMapping("/point")
    public ResponseEntity setPoint(@Valid @ModelAttribute("checkForm") PointsCredentials point, @NotNull BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
        point.setResult(point.getX(), point.getY(), point.getR());
        try {
            pointService.register(point);
        } catch (IllegalArgumentException e) {
            Map<String, String> resp = new HashMap<>();
            resp.put("error", "error");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(resp);
        }
        List<Point> points;
        points = pointService.getAllPointsByUser(userRepository.getUserByLogin(point.getLogin()));
        StringJoiner joiner;
        joiner = new StringJoiner(",");
        for (int i = 0; i < points.size(); i++) {
            Point point1;
            point1 = points.get(i);
            StringBuilder builder;
            builder = new StringBuilder();
            for (String s : Arrays.asList("{\"x\":\"", String.format("%.2f", point1.getX()), "\", \"y\":\"", String.format("%.2f", point1.getY()), "\", \"r\":\"", String.format("%.2f", point1.getR()), "\", \"result\":\"", point1.getResult(), "\"}")) {
                builder.append(s);
            }
            joiner.add(builder.toString());
        }
        return ResponseEntity.ok("[" + joiner + "]");
    }
}
