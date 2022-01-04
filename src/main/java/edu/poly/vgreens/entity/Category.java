package edu.poly.vgreens.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 64,nullable = false)
    String name;
    
    @Column(nullable = false)
    String image;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Product> products;
    
    @Transient // thuộc tính không liên quan đến database
    private String img;

}
