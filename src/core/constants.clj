(ns core.constants)

;; ==============================
;; OpenAPI constants
;; ==============================

;; OpenAPI schema filename. Shall be created into resources/
(def OPENAPI-SCHEMA-FILE "openapi.yml")

;; OpenAPI JSON file route
(def OPENAPI-JSON-DOCS-ROUTE "/openapi.json")

;; OpenAPI swagger docs route
(def OPENAPI-SWAGGER-DOCS-ROUTE "/swagger-ui")

;; ==============================
;; Application config
;; ==============================

;; Application config filename. Shall be created into resources/
(def APPLICATION-CONFIG-FILE "config.edn")

;; The size of the standard sample of links
(def BASIC-LINKS-PULL-SIZE 50)

;; Symbols which may be used into code generation
(def GENERATION-ALPHABET
  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")