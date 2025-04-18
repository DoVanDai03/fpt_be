package com.fpt_be.fpt_be.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "ten_chuc_vu")
    private String tenChucVu;
    
    @Column(name = "tinh_trang")
    private Boolean tinhTrang;
    
    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private Set<Admin> admins;
    
    @OneToMany(mappedBy = "position")
    @JsonIgnore
    private Set<PositionPermission> positionPermissions;
    
    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;
    
    @Column(name = "ngay_cap_nhat")
    private LocalDateTime ngayCapNhat;

    @PrePersist
    protected void onCreate() {
        ngayTao = LocalDateTime.now();
        ngayCapNhat = LocalDateTime.now();
        if (tinhTrang == null) {
            tinhTrang = true; // Set default value to true when creating new position
        }
    }

    @PreUpdate
    protected void onUpdate() {
        ngayCapNhat = LocalDateTime.now();
    }
} 