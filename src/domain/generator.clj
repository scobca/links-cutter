(ns domain.generator)

(def alphabet
  "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")

(defn check-valid-size [size]
  (and
   (>= size 5)
   (<= size 10)))

(defn generate-code
  ([]
   (generate-code 6))
  ([size]
   (when (check-valid-size size)
     (apply str
            (repeatedly
             size
             (fn []
               (nth alphabet (rand-int (count alphabet)))))))))