# Clojure Links Cutter

![CI Status](https://img.shields.io/github/actions/workflow/status/scobca/links-cutter/ci.yml?branch=main)
![License](https://img.shields.io/github/license/scobca/links-cutter)
![Release](https://img.shields.io/github/v/release/scobca/links-cutter)
![Language](https://img.shields.io/github/languages/top/scobca/links-cutter)

## What is that?

`Clojure links cutter` is a studying project for learning Clojure syntax and its usability in WebDev.

## Project structure

```markdown
├── .github/workflows
│ └── ci.yml
├── scripts/
│ └── tag-release.sh            # Script for automatic version tagging
├── src/
│ ├── core/
│ │ ├── constants.clj           # Global application constants
│ │ ├── memory.clj              # In-memory storage implementation (memory-model)
│ │ ├── time.clj                # Uptime server timer
│ ├── domain/
│ │ ├── generator.clj           # Short code generation logic
│ │ └── link.clj                # Link domain model and validation
│ ├── dto/
│ │ ├── link.clj           
│ │ └── time.clj           
│ ├── web/
│ │ ├── routes/
│ │ │ ├── links.clj             # /links routes
│ │ │ ├── service.clj           # application-level routes
│ │ └── router.clj              # Global application router
│ └── core.clj                  # Main application entry point
├── .cljfmt.edn                 # Clojure linter requirements
├── .gitignore
├── .markdownlint.yaml
├── license.md
├── project.clj                 # Lein project settings
├── readme.md
└── VERSION
```

## Authors

[scobca](https://github.com/scobca) — Maintainer
