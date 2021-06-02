import org.jooq.*;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static test.generated.Tables.AUTHOR;

public class JooqCodeGenQuery {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useSSL=false", "root", "aman1234")) { // (1)
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
//            insertAuthor(create);
//            selectAuthor(create);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private static void selectAuthor(DSLContext create)  {

            Result<Record> result = create.select().from(AUTHOR).fetch();

            for (Record r : result) {
                Integer id = r.getValue(AUTHOR.ID);
                String firstName = r.getValue(AUTHOR.FIRST_NAME);
                String lastName = r.getValue(AUTHOR.LAST_NAME);
                System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
            }

    }

    public static void insertAuthor(DSLContext create)  {

            create.insertInto(AUTHOR,
                    AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                    .values(103, "Dipak", "Kumar")
                    .execute();


    }


}
