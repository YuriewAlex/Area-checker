package com.ifmo.dbInterface;

import com.ifmo.db.Point;
import com.ifmo.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> getPointsByUser(User user);
}
