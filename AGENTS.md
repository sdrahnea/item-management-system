# AGENTS.md

## Project Snapshot
- Stack: Spring Boot + JoinFaces (JSF/PrimeFaces) + Spring Data JPA + Spring Security (`pom.xml`).
- Runtime defaults: port `8081`, context path `/ims`, MySQL datasource `jdbc:mysql://localhost:3306/ims` (`src/main/resources/application.properties`).
- Entry point is `ItemManagementSystemApplication` with `@EnableJpaRepositories` (`src/main/java/com/ims/ItemManagementSystemApplication.java`).

## Architecture You Need To Know
- The app mixes JSF page flows and REST endpoints in the same controllers.
- `AbstractController<T>` provides:
  - JSF CRUD/navigation methods: `save/remove/edit/cancel` returning view names like `dashboardList`.
  - REST endpoints on the same bean: `GET/POST/PUT/DELETE` (`src/main/java/com/ims/controller/AbstractController.java`).
- Feature controllers are thin (`ChartTypeController`, `DashboardController`, `ConstantController`) and only set `@RequestMapping` + entity type.
- `AbstractService<T>` is a generic CRUD layer that autowires `CrudRepository<T,Long>` by type; concrete services are mostly marker classes (`src/main/java/com/ims/service`).

## Main Data Flow (Dashboard)
- Admin configures dashboard rows in `dashboardView.xhtml` via `dashboardController.selectedObject`.
- `DashboardViewController.initialise()` loads all dashboards, executes each stored native SQL query, then builds PrimeFaces chart models (`BarChartModel`, `HorizontalBarChartModel`, `PieChartModel`).
- Chart rendering happens from `dashboard.xhtml` using `#{dashboardViewController.graphicList}` (`src/main/java/com/ims/controller/DashboardViewController.java`).
- `Dashboard` model stores chart behavior as DB fields (`query`, `showColumn`, `seriesTags`, `legendPosition`, `ymin/ymax`) and relation references (`chartType`, `show`, `animate`) (`src/main/java/com/ims/model/Dashboard.java`).

## Security/Auth Wiring
- Security config permits `/login.xhtml`, requires auth for `/main.xhtml`, uses form login + BCrypt encoder (`src/main/java/com/ims/configs/SecurityConfig.java`).
- User details come from `SecurityUserService` -> `SecurityUserDto.findByUsername(...)` -> `users` table (`src/main/java/com/ims/service/impl/SecurityUserService.java`).
- Seeded admin insert is commented in `src/main/resources/data.sql`; keep credentials/password hashing aligned with `BCryptPasswordEncoder`.

## Conventions And Gotchas In This Repo
- JSF pages live under `src/main/resources/META-INF/resources` and rely on outcome strings (for example `outcome="dashboardList"`).
- Converter IDs (`constantConverter`, `chartTypeConverter`) are referenced directly in XHTML select menus.
- `Dashboard.query` is executed as native SQL without sanitization; treat dashboard edits as privileged/admin-only config.
- This codebase contains legacy Spring Boot 1.x APIs (`WebSecurityConfigurerAdapter`, `CrudRepository.findOne/delete(Long)`), so avoid "modernizing" signatures unless doing a coordinated upgrade.

## Developer Workflow
- Build/package from README: `mvn clean package` then `java -jar target/item-management-system-0.0.1-SNAPSHOT.jar`.
- There are currently no tests (`README.md` explicitly states this).
- SQL bootstrap files are manual: `chart_type.sql` has initial chart types; `data.sql` is mostly placeholders.
- In this environment, Maven CLI was not available (`mvn` not recognized), so ensure Maven is installed before relying on scripted build steps.

## High-Value Files For Fast Onboarding
- `src/main/java/com/ims/controller/AbstractController.java`
- `src/main/java/com/ims/controller/DashboardViewController.java`
- `src/main/java/com/ims/service/AbstractService.java`
- `src/main/java/com/ims/model/Dashboard.java`
- `src/main/resources/META-INF/resources/dashboard*.xhtml`
- `src/main/java/com/ims/configs/SecurityConfig.java`

