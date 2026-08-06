(ns domain.generator
  (:require [core.config :refer [link-code-length]]
            [core.constants :refer [GENERATION-ALPHABET]]))

(defn valid-size?
  "Validate the input size of generated code, returns boolean."
  [size]
  (and
   (integer? size)
   (>= size 5)
   (<= size 10)))

(defn generate-code
  "Create abstract code for links records."
  ([]
   (generate-code (link-code-length)))

  ([size]
   (when (valid-size? size)
     (apply str
            (repeatedly
             size
             (fn [] (nth GENERATION-ALPHABET (rand-int (count GENERATION-ALPHABET)))))))))
