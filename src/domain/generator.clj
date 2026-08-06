(ns domain.generator
  (:require [core.config :refer [link-code-length]]))

(def alphabet
  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")

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
             (fn [] (nth alphabet (rand-int (count alphabet)))))))))
