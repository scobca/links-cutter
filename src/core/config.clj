(ns core.config
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [core.constants :refer [APPLICATION-CONFIG-FILE]]))

(defonce ^:private config
  (delay
    (if-let [resource (io/resource APPLICATION-CONFIG-FILE)]
      (-> resource
          slurp
          edn/read-string)
      (throw (Exception. "config.edn not found in resources")))))

(defn http-port
  "Web server http port"
  [] (-> @config :http-port))

(defn link-code-length
  "Length of special short code for links records"
  [] (-> @config :link-code-length))