package com.duzceuniversity.kurumtakip.DataBase;

import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.Country;
import com.duzceuniversity.kurumtakip.DataBase.Model.address.District;
import com.duzceuniversity.kurumtakip.DataBase.Repository.UserRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.CountryRepository;
import com.duzceuniversity.kurumtakip.DataBase.Repository.address.DistrictRepository;
import com.duzceuniversity.kurumtakip.Service.StaffService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserCreateAndInsertToDB implements CommandLineRunner {
    private UserRepository userRepository;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    StaffService staffService;

    public UserCreateAndInsertToDB(UserRepository userRepository,DataSource dataSource) {
        this.userRepository = userRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().size() == 0) {
            List<Map<String, Object>> DbConfig = getData();
            for (Map<String, Object> stringObjectMap : DbConfig) {
                jdbcTemplate.execute(stringObjectMap.get("sql_statements").toString());
            }
            getParameters();
            Country country = countryRepository.findByCountryCode("TR");
            District district = districtRepository.findByDistrictMernisKod(1554);
            User demo = new User("demo", passwordEncoder.encode("demo"), "Demo", "DEMO", "demo@demo.com", "ADMIN", country, district);
            User admin = new User("admin", passwordEncoder.encode("admin"), "Admin", "ADMIN", "admin@admin.com", "ADMIN", country, district);
            List<User> users = Arrays.asList(admin, demo);
            this.userRepository.saveAll(users);
            System.out.println("Database Kurulumu Tamamlandı....................................");
            System.out.println("User Kayıtları Tamamlandı.......................................");
        }else {
            staffService.UniqTest();
        }
    }

    final static String SQL =
            "SELECT  CONCAT('ALTER TABLE `', table_name, '` ENGINE=InnoDB;') AS sql_statements " +
                    "FROM    information_schema.tables AS tb " +
                    "WHERE   table_schema = 'kurumtakip' " +
                    "AND     `ENGINE` = 'MyISAM' " +
                    "AND     `TABLE_TYPE` = 'BASE TABLE' " +
                    "ORDER BY table_name DESC";

    public List<Map<String, Object>> getData() {
        return jdbcTemplate.queryForList(SQL);
    }

    public void getParameters() throws IOException {
        Resource resource = new ClassPathResource("/database_parametreleri.txt");
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(Objects.requireNonNull(classLoader.getResource("database_parametreleri.txt")).getFile());
        File file = resource.getFile();
        System.out.println("File imported...");
        PrintWriter outputStream = new PrintWriter(new FileWriter("LastInserts.txt"));
        System.out.println("Last inserts created...");
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            String lineNew = "";
            boolean tF = false;
            while (it.hasNext()) {
                String line = it.nextLine();
                if (line.contains("INSERT INTO kurumtakip")) {
                    tF = true;
                }
                if (tF) {
                    if (line.contains("INSERT INTO kurumtakip") && line.contains(");")) {
                        lineNew += line;
                        jdbcTemplate.execute(lineNew);
                        lineNew = "";
                    } else if (line.contains("INSERT INTO kurumtakip")) {
                        lineNew += line;
                    } else if (line.contains(");")) {
                        lineNew += line;
                        jdbcTemplate.execute(lineNew);
                        outputStream.println(lineNew);
                        lineNew = "";
                    } else if (!line.contains("*")) {
                        lineNew += line;
                    }
                }
                if (line.contains(");")) {
                    tF = false;
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
