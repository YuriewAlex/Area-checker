package com.ifmo.input;

import com.ifmo.db.Point;
import com.ifmo.db.User;
import com.ifmo.input.PointsCredentials;
import com.ifmo.dbInterface.PointRepository;
import com.ifmo.dbInterface.UserRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PointService {
    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    public PointService(UserRepository userRepository, PointRepository pointRepository){
        this.userRepository = userRepository;
        this.pointRepository = pointRepository;
    }

    @NotNull
    @NotEmpty
    public Point register(PointsCredentials pointsCredentials) throws IllegalArgumentException{
        Point point = new Point();
        point.setX(Double.parseDouble(pointsCredentials.getX()));
        point.setY(Double.parseDouble(pointsCredentials.getY()));
        point.setR(Double.parseDouble(pointsCredentials.getR()));
        point.setUser(userRepository.getUserByLogin(pointsCredentials.getLogin()));
        if(point.getUser() == null){
             throw new IllegalArgumentException();
        }
        point.setResult(pointsCredentials.isResult());
        pointRepository.save(point);
        return point;
    }

    public List<Point> getAllPointsByUser(User user){
       return pointRepository.getPointsByUser(user);
    }
}
