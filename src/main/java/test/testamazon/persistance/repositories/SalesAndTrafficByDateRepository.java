package test.testamazon.persistance.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import test.testamazon.persistance.entities.report.SalesAndTrafficByDate;

public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, String> {
    SalesAndTrafficByDate findByDate(String date);
}
