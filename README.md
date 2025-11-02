#  Bank Statement Generator App

A **Spring Boot + PostgreSQL + Mailtrap** application that generates monthly **bank statements in PDF format** and can **email them automatically** to users.

---

##  Features

-  Generate detailed PDF statements for any account and month.
-  Send statements as email attachments using Mailtrap (or Gmail SMTP).
-  Store transactions in a PostgreSQL database.
-  RESTful API for statement download and email dispatch.
-  Built with clean modular layers:
  - `controller/` – API endpoints  
  - `service/` – business logic  
  - `repo/` – data access (Spring Data JPA)  
  - `entity/` – JPA entities  
  - `util/` – helper utilities (PDF generator)

---

##  Tech Stack

| Component | Technology |
|------------|-------------|
| Backend | Spring Boot 3 (Java 17) |
| Database | PostgreSQL |
| ORM | Hibernate / JPA |
| Mail Service | Mailtrap SMTP (sandbox testing) |
| PDF Generation | iText / OpenPDF |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |

---

## Screenshots of the output
<img width="1310" height="723" alt="Screenshot 2025-11-02 182737" src="https://github.com/user-attachments/assets/cc5f22cb-c37d-488c-91c4-27b13a9b35fa" />

<img width="1596" height="364" alt="Screenshot 2025-11-02 182720" src="https://github.com/user-attachments/assets/55d443ab-4d87-4849-bee7-6f7af0f871ab" />


<img width="1250" height="824" alt="Screenshot 2025-11-02 174222" src="https://github.com/user-attachments/assets/2f031d6f-b175-4b21-9aee-26d370535a8d" />

<img width="1265" height="847" alt="Screenshot 2025-11-02 173946" src="https://github.com/user-attachments/assets/34ccd7e8-3bcf-4c40-b165-47e2a03d4e6a" />
