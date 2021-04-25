package com.duzceuniversity.kurumtakip.DataBase.Repository;

import com.duzceuniversity.kurumtakip.DataBase.Model.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UserCreateAndInsertToDB implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JdbcTemplate jdbcTemplate;

    public UserCreateAndInsertToDB(UserRepository userRepository, PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findAll().size() == 0) {
            List<Map<String, Object>> DbConfig = getData();
            for (Map<String, Object> stringObjectMap : DbConfig) {
                jdbcTemplate.execute(stringObjectMap.get("sql_statements").toString());
            }
            List<String> parameters = getParameters();
            User demo = new User("demo", passwordEncoder.encode("demo"), "ADMIN", "", "admin", "admin");
            User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN", "", "admin", "admin");
            List<User> users = Arrays.asList(admin, demo);
            this.userRepository.saveAll(users);
            System.out.println("Database Kurulumu Tamamlandı....................................");
            System.out.println("User KAyıtları Tamamlandı.......................................");
        }
    }

    final static String SQL =
            "SELECT  CONCAT('ALTER TABLE `', table_name, '` ENGINE=InnoDB;') AS sql_statements " +
                    "FROM    information_schema.tables AS tb " +
                    "WHERE   table_schema = 'tein' " +
                    "AND     `ENGINE` = 'MyISAM' " +
                    "AND     `TABLE_TYPE` = 'BASE TABLE' " +
                    "ORDER BY table_name DESC";

    public List<Map<String, Object>> getData() {
        return jdbcTemplate.queryForList(SQL);
    }

    public List<String> getParameters() throws IOException {
        PrintWriter outputStream = new PrintWriter(new FileWriter("LastInserts.txt"));
        System.out.println("Last inserts created...");
//        InputStream is=this.getClass().getResourceAsStream("classpath:database_parametreleri.txt");
//        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        Resource resource = new ClassPathResource("C:\\database_parametreleri.txt");
        File file = resource.getFile();
        System.out.println("File imported...");
        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            String lineNew = "";
            boolean tF = false;
            while (it.hasNext()) {
                String line = it.nextLine();
                if (line.contains("INSERT INTO tein")) {
                    tF = true;
                }
                if (tF) {
                    if (line.contains("INSERT INTO tein") && line.contains(");")) {
                        lineNew += line;
                        jdbcTemplate.execute(lineNew);
                        lineNew = "";
                    } else if (line.contains("INSERT INTO tein")) {
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
        return null;
    }
}
