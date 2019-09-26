package assigment.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import assigment.products.entity.ProductVO;

@Repository
public interface ProductRepository extends JpaRepository<ProductVO, Long> {
}
