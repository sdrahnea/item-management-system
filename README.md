<div align="center">

# 📦 Item Management System

**A self-hosted procurement request platform for small and medium-sized businesses.**  
Employees submit item requests; office managers consolidate and place orders — no chat threads, no spreadsheet ping-pong.

[![Java](https://img.shields.io/badge/Java-8-007396?logo=java&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-1.5.7-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PrimeFaces](https://img.shields.io/badge/PrimeFaces-JoinFaces%202.4.1-0076BE)](https://www.primefaces.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8%2B-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE.md)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

</div>

---

## ✨ Features

| Feature | Details |
|---|---|
| 📊 **Configurable Dashboards** | Build bar, horizontal-bar, and pie charts driven by custom native SQL queries stored in the DB |
| 🔒 **Secured Access** | Spring Security form login with BCrypt password hashing; role-based via `SecurityRole` |
| 🗄️ **Multi-DB Support** | Ships with MySQL (default) and PostgreSQL drivers; switch by editing `application.properties` |
| 📤 **Data Export** | One-click export of any table to **XLS, CSV, XML** (Apache POI + iText) |
| 🌐 **REST API** | Every entity exposes full CRUD REST endpoints alongside the JSF UI |
| 🎨 **Themeable UI** | 30+ PrimeFaces themes selectable via a single property |

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────┐
│                  Browser / REST Client               │
└────────────────────┬────────────────────────────────┘
                     │  JSF (PrimeFaces)  +  HTTP REST
┌────────────────────▼────────────────────────────────┐
│              Controllers  (Spring Beans)             │
│  AbstractController<T>  ─  JSF actions + REST CRUD  │
│  DashboardViewController  ─  chart model builder    │
└────────────────────┬────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────┐
│               Services  (AbstractService<T>)         │
│        Generic JPA CRUD; autowired by entity type    │
└────────────────────┬────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────┐
│          Repositories  (CrudRepository<T, Long>)     │
└────────────────────┬────────────────────────────────┘
                     │
              ┌──────▼──────┐
              │  MySQL / PG  │
              └─────────────┘
```

> Each feature module (`Dashboard`, `ChartType`, `Constant`) follows the same three-layer pattern — adding a new entity means one model, one service, one controller, and two XHTML views.

---

## 🚀 Quick Start

### Prerequisites

| Tool | Version |
|---|---|
| Java JDK | 1.8+ |
| Maven | 3.5+ |
| MySQL | 5.7+ / 8.0+ |

### 1. Clone

```bash
git clone https://github.com/sdrahnea/item-management-system.git
cd item-management-system
```

### 2. Set Up the Database

```sql
CREATE DATABASE ims;
```

Run the bootstrap scripts in order:

```bash
# Seeds chart types (bar, horizontal-bar, pie)
mysql -u root -p ims < src/main/resources/chart_type.sql

# Seeds boolean constants and the default admin user
mysql -u root -p ims < src/main/resources/data.sql
```

> **MySQL 8.0.4+** — if you hit an authentication error, run:
> ```sql
> ALTER USER 'your_user'@'localhost' IDENTIFIED WITH mysql_native_password BY 'your_password';
> ```

### 3. Configure

Edit `src/main/resources/application.properties`:

| Property | Default | Description |
|---|---|---|
| `server.port` | `8081` | HTTP port |
| `server.context-path` | `/ims` | Application root path |
| `spring.datasource.url` | `jdbc:mysql://localhost:3306/ims` | JDBC URL |
| `spring.datasource.username` | `root` | DB username |
| `spring.datasource.password` | `root` | DB password |
| `jsf.primefaces.theme` | `redmond` | UI theme (any PrimeFaces theme name) |

**PostgreSQL** is also supported — comment out the MySQL block and uncomment the PostgreSQL block in `application.properties`.

### 4. Build & Run

```bash
mvn clean package
java -jar target/item-management-system-0.0.1-SNAPSHOT.jar
```

Open [http://localhost:8081/ims/login.xhtml](http://localhost:8081/ims/login.xhtml)

**Default credentials:** `admin` / `123`

---

## 🌐 REST API

Every entity controller inherits full CRUD REST endpoints from `AbstractController<T>`.  
Base URL: `http://localhost:8081/ims`

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/dashboard` | List all dashboards |
| `GET` | `/dashboard/{id}` | Get dashboard by ID |
| `POST` | `/dashboard` | Create a dashboard |
| `PUT` | `/dashboard` | Update a dashboard |
| `DELETE` | `/dashboard/{id}` | Delete a dashboard |

The same pattern applies to `/chart-type` and `/constant`.

### Example — Create a Dashboard

**Request**
```http
POST /ims/dashboard
Content-Type: application/json

{
  "name": "Monthly Orders",
  "query": "SELECT month, total FROM order_summary ORDER BY month",
  "showColumn": "1",
  "seriesTags": "Total Orders",
  "legendPosition": "ne",
  "ymin": 0,
  "ymax": 0,
  "chartType": { "id": 1 },
  "show":     { "id": 1 },
  "animate":  { "id": 1 }
}
```

**Response** `200 OK`
```json
{
  "id": 7,
  "name": "Monthly Orders",
  "query": "SELECT month, total FROM order_summary ORDER BY month",
  "showColumn": "1",
  "seriesTags": "Total Orders",
  "legendPosition": "ne",
  "ymin": 0,
  "ymax": 0,
  "chartType": { "id": 1, "name": "BarChartModel", "uiType": "bar" },
  "show":      { "id": 1, "name": "true" },
  "animate":   { "id": 1, "name": "true" },
  "createdDate": "2026-03-29T10:00:00.000+0000"
}
```

> `chartType.id`, `show.id`, and `animate.id` must reference existing rows seeded by `chart_type.sql` and `data.sql`.

---

## 🗃️ Database Support

| Database | Status | Notes |
|---|---|---|
| MySQL 5.7+ | ✅ Default | Requires `mysql_native_password` on MySQL 8.0.4+ |
| PostgreSQL | ✅ Supported | Uncomment PG block in `application.properties` |
| H2 | ⚠️ Partial | In-memory mode works; not validated for production use |

---

## 🧰 Built With

| Library | Purpose |
|---|---|
| [Spring Boot 1.5.7](https://spring.io/projects/spring-boot) | Application framework & embedded Tomcat |
| [JoinFaces 2.4.1](https://github.com/joinfaces/joinfaces) | JSF + PrimeFaces integration for Spring Boot |
| [PrimeFaces](https://www.primefaces.org/) | UI component library (charts, tables, layout) |
| [Spring Security](https://spring.io/projects/spring-security) | Authentication & authorization |
| [Spring Data JPA](https://spring.io/projects/spring-data-jpa) | Repository-pattern data access |
| [Apache POI 3.17](https://poi.apache.org/) | XLS / XLSX data export |
| [iText 2.1.7](https://itextpdf.com/) | PDF data export |
| [Lombok](https://projectlombok.org/) | Boilerplate reduction (`@Data`, `@Builder`, etc.) |
| [MySQL Connector](https://dev.mysql.com/downloads/connector/j/) | JDBC driver |

---

## 🤝 Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for the code of conduct and pull-request process.  
We follow [SemVer](http://semver.org/) for versioning.

---

## 👤 Authors

**Sergiu Drahnea** — [LinkedIn](https://www.linkedin.com/in/sergiu-drahnea)

---

## 📄 License

This project is licensed under the **MIT License** — see [LICENSE.md](LICENSE.md) for details.

---

## ☕ Support the Project

If this project saved you time, consider a small donation:

| Method | Address |
|---|---|
| [PayPal](https://www.paypal.me/sdrahnea) | paypal.me/sdrahnea |
| EGLD | `erd1t3t5m8v7862asdh48nq820shsmlmuw9jpm87qw25cvch7djpkapskgq4es` |
| TRX / BTT | `TRe8xSkGqpS73Nhk6bnvW34aiJoRTmZs8N` |
| HOT / VET | `0x1ebfc62e2510f0a0558568223d1d101d0cf074b2` |
| TROY / PHB | `bnb136ns6lfw4zs5hg4n85vdthaad7hq5m4gtkgf23` (Memo: `100079140`) |
