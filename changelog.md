# Changelog

The format is based on [Keep a Changelog](https://keepachangelog.com/ru/1.0.0),
and this project follows [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [v0.1.4] - 2026-08-08

### Added

- created `web.routes.openapi.clj` - routes with raw openapi specifications
- created `/sqagger-ui` endpoint with openapi visualization
- created openapi specification for system & links endpoints

## [v0.1.3] - 2026-08-07

### Added

- created application config file - `resources/config.edn`
- created application config getters - `core.config.*`

### Changed

- `core`, `domain.generator` now using properties from `config.edn`
- code generation alphabet moved from `domain.generator` to `core.constants`

### Fixed

- fixed `domain.link` formatting

## [v0.1.2] - 2026-08-06

### Added

- added status badges into readme.md

### Changed

- updated project structure into readme.md

## [v0.1.1] - 2026-08-06

### Added

- created Links record deftype - `dto.links.LinksRecord`

### Changed

- renamed links dto namespace `dto.link_request.clj` –> `dto.link.clj`
- updated `domain.links.create-link-record!` function return statement
- updated `test.domain.link_test.create-link-record!` function testcases based on new return statement
- updated core function tests - there\`re moved into new namespace `test.core.*`  

## [v0.1.0] - 2026-08-04

### Added

- created jetty web-server core
- created application time manager – `ns core.time`
- created basic application api (links manager routes & service routes) – `ns web.routes.*`
- created global application router – `ns web.router`
- created application constants storage – `ns core.constants`

### Changed

- updated application structure ––> moved common project components into `core.*` namespace
- updated object definition ––> created external DTOs and moved into `dto.*` namespace

## [v0.0.3] - 2026-08-01

### Changed

Project moved to Lein base. Base project structure now in project.clj file
Due to CI steps project version into project.clj getting from VERSION file using slurp function

## [v0.0.2] - 2026-07-31

### Added

- added unit tests for project domain model
- added automatic ci step for running tests

### Changed

- updated deps.edn file

## [v0.0.1.3] - 2026-07-29

> This version format will be used only before v0.0.2 (or v0.1.0) due to project instability and frequent releases.

### Added

- added changelog.md

### Changed

- disable md-024 lint rule (No duplicate heading)

## [v0.0.1.2] - 2026-07-29

> This version format will be used only before v0.0.2 (or v0.1.0) due to project instability and frequent releases.

### Added

- added the project structure into readme.md
- added inline docs for clojure-functions into domain model
- added extra functions for work with collection (find, remove, create)

### Changed

- changed show-links function signature and logic - now it's only returns struct of link records

## [v0.0.1.1] - 2026-07-29

> This version format will be used only before v0.0.2 (or v0.1.0) due to project instability and frequent releases.

### Fixed

- updated tag-release.sh script for using into local machine

## [v0.0.1] - 2026-07-29

### Added

- added basic logic of links-cutter domain model (core, memory model, links manager, codes generator)
- added automatic pipelines for project validating & deployments
- added first documentation
