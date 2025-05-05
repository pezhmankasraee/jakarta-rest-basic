# jakarta-rest-basic
This is a RESTful application developed in Java using Jakarta EE


Here’s a concise presentation outline summarizing the steps and troubleshooting we did together to get your **Jakarta EE 11 application working with GlassFish and H2 database**:

---

## **Jakarta EE 11 + GlassFish + H2 Integration: Step-by-Step**

---

### **1. Application Setup**

- **Created a Jakarta EE 11 project** with an entity class (`Student`) annotated with `@Entity`.
- Configured `persistence.xml` to use a JTA datasource and enabled automatic schema generation with:
  ```xml
    <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
  ```
- Ensured the entity was correctly listed in the persistence unit.

---

### **2. GlassFish Server Configuration**

- **Added the H2 JDBC driver** (`h2*.jar`) to the GlassFish domain library (`domains/domain1/lib/`)[6][11].
- **Created a JDBC Connection Pool** in the GlassFish Admin Console:
    - Set the datasource class to `org.h2.jdbcx.JdbcDataSource`.
    - Configured properties: `user`, `password`, and `url` (JDBC URL for H2).
- **Created a JDBC Resource** (JNDI name, e.g., `jdbc/h2DS`) linked to the connection pool[6][8][10].
- **Tested the connection pool** using the “Ping” feature in the Admin Console to ensure connectivity.

---

### **3. H2 Database Configuration**

- Matched the **JDBC URL** in both GlassFish and the H2 Console to ensure both pointed to the same database file.
- Switched from using `~` (home directory) to an **absolute path** to avoid user path mismatches.
- **Added `;MODE=LEGACY` to the JDBC URL** to resolve compatibility issues with H2 2.x and EclipseLink’s DDL syntax for identity columns.

---

### **4. Troubleshooting & Resolution**

- **Checked server logs** for deployment and schema generation errors (enabled/adjusted logging as needed).
- Diagnosed and fixed a critical error:
    - EclipseLink-generated DDL was incompatible with H2 2.x’s new identity column syntax.
    - Solution: Added `;MODE=LEGACY` to the JDBC URL in both GlassFish and H2 Console, enabling legacy compatibility mode and allowing table creation to succeed.
- **Verified table creation** by inspecting the database in the H2 Console after deployment.

---

### **5. Key Lessons & Best Practices**

- Always ensure JDBC URLs are identical in GlassFish and H2 Console for consistent database access.
- For H2 2.x+, use `;MODE=LEGACY` if your JPA provider generates legacy DDL.
- Use absolute paths in JDBC URLs to avoid user-specific database locations.
- Enable and check server logs for troubleshooting deployment and persistence issues.
- JPA schema generation (`create` or `drop-and-create`) will create tables automatically at deployment-no need to insert data first.

---

**Would you like this as a slide deck outline or a more detailed document for sharing with your team?**

Citations:
[1] https://jakarta.ee/xml/ns/jakartaee/
[2] https://www.jetbrains.com/help/idea/creating-and-running-your-first-jakarta-ee-application.html
[3] https://newsroom.eclipse.org/eclipse-newsletter/2024/july/developer%E2%80%99s-guide-jakarta-ee-11
[4] https://jakarta.ee/learn/docs/jakartaee-tutorial/current/web/faces-configure/faces-configure.html
[5] https://eclipse-ee4j.github.io/jakartaee-tutorial/
[6] https://keithrieck.wordpress.com/2010/02/23/h2-database-on-glassfish/
[7] https://galaxy.ai/youtube-summarizer/a-comprehensive-developers-guide-to-jakarta-ee-11-s9C8HQqYOlY
[8] https://groups.google.com/g/h2-database/c/F_liUgK552k
[9] https://marciowb.wordpress.com/2008/08/07/quickly-using-h2-database-with-glassfish-and-toplink/
[10] https://docs.oracle.com/cd/E19798-01/821-1751/ggndx/index.html
[11] https://stackoverflow.com/questions/10663126/connecting-to-h2-database-with-glassfish

---
Answer from Perplexity: https://www.perplexity.ai/search/i-am-going-to-use-persistence-NBRlJQ2QTcuPEFDD9r79gA?31=d&utm_source=copy_output





If you switch from H2 to MySQL with GlassFish, the process is **very similar**-but you need to use the MySQL JDBC driver (`mysql-connector-java-x.x.xx.jar`) instead of the H2 driver. Here are the main differences and steps:

---

## **Key Differences: H2 vs. MySQL with GlassFish**

| Step                | H2 Database                                    | MySQL Database                                   |
|---------------------|------------------------------------------------|--------------------------------------------------|
| **JDBC Driver**     | `h2*.jar`                                      | `mysql-connector-java-x.x.xx.jar`                |
| **Driver Location** | `domains/domain1/lib/`                         | `domains/domain1/lib/` (same location)[2][5][7]  |
| **JDBC URL**        | `jdbc:h2:/path/to/db;MODE=LEGACY`              | `jdbc:mysql://host:port/dbname`                  |
| **Datasource Class**| `org.h2.jdbcx.JdbcDataSource`                  | `com.mysql.jdbc.jdbc2.optional.MysqlDataSource`  |
| **Extra Steps**     | Sometimes need `;MODE=LEGACY` for H2 2.x+      | No legacy mode needed, but use compatible driver  |

---

## **Steps to Use MySQL with GlassFish**

1. **Download MySQL JDBC Driver**
    - Get `mysql-connector-java-x.x.xx.jar` from the [official MySQL site][7].

2. **Copy the Driver to GlassFish**
    - Place the JAR in `glassfish/domains/domain1/lib/` (same as with H2)[2][5][7].

3. **Restart GlassFish**
    - So the server picks up the new driver.

4. **Create a JDBC Connection Pool**
    - In the GlassFish Admin Console:
        - **Resource Type:** `javax.sql.DataSource`
        - **Datasource Classname:** `com.mysql.jdbc.jdbc2.optional.MysqlDataSource`
        - **Properties:**
            - `user`: your MySQL username
            - `password`: your MySQL password
            - `url`: `jdbc:mysql://host:port/dbname`
    - Ping the pool to verify connectivity[2][5][7].

5. **Create a JDBC Resource**
    - Link it to the connection pool.
    - Use the JNDI name (e.g., `jdbc/myDS`) in your `persistence.xml`.

6. **Update Your Application**
    - Reference the new JNDI name in `persistence.xml`.
    - No need for `;MODE=LEGACY` or H2-specific settings.

---

## **Summary**

- **Both H2 and MySQL drivers must be copied to `domains/domain1/lib/`.**
- **JDBC URL, datasource class, and connection properties will differ.**
- **No need for `;MODE=LEGACY` with MySQL.**
- **Use the correct MySQL driver version compatible with your GlassFish version** (some newer versions may not work with older GlassFish)[2][7].

---

If you’d like, I can provide a sample `persistence.xml` or step-by-step screenshots for the MySQL setup. Would that help?

Citations:
[1] https://glassfish.org/docs/5.1.0/administration-guide/jdbc.html
[2] https://nikals.se/2019/04/05/using-mysql-connector-j-jdbc-driver-with-glassfish/
[3] https://docs.oracle.com/cd/E18930_01/html/821-2416/beamw.html
[4] https://docs.oracle.com/cd/E19798-01/821-1751/beamw/index.html
[5] https://www.youtube.com/watch?v=5v5h1ZkfcFU
[6] https://stackoverflow.com/questions/50467292/setting-up-glassfish-datasource-issue
[7] https://javatutorial.net/configure-glassfish-mysql
[8] https://javaeesquad.github.io/tutorials/glassfishDatasource/glassFishDatasource.html

---
Answer from Perplexity: https://www.perplexity.ai/search/i-am-going-to-use-persistence-NBRlJQ2QTcuPEFDD9r79gA?31=d&utm_source=copy_output