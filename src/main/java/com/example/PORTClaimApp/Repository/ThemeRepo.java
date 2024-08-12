package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepo extends JpaRepository<Theme,Long> {
}
