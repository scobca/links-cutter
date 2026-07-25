(ns memory)

(def links (atom {}))

(defn code-occupied? [code]
  (contains? @links code))

(defn save-link-record!
  [code url]
  (swap! links assoc code url))

(defn remove-link-record!
  [code]
  (swap! links dissoc code))