(ns core.constants)

;; Application config filename. Shall be created into resources/
(def APPLICATION-CONFIG-FILE "config.edn")

;; The size of the standard sample of links
(def BASIC-LINKS-PULL-SIZE 50)

;; Symbols which may be used into code generation
(def GENERATION-ALPHABET
  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")