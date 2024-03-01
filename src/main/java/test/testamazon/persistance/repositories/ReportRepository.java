package test.testamazon.persistance.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import test.testamazon.persistance.entities.report.ReportEntity;

public interface ReportRepository extends MongoRepository<ReportEntity, String> {

}