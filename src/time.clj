(ns time
  (:import [java.time Duration Instant]))

(defrecord Uptime [hours minutes seconds millis])

(defonce start-time (atom nil))

(defn ^:private convert-java-duration
  "Convert java Instant time into custom Uptime class.
  Returns: time.Uptime"
  [^Duration duration]
  (let [hours (.toHours duration)
        minutes (mod (.toMinutes duration) 60)
        seconds (mod (.getSeconds duration) 60)
        millis (quot (.getNano duration) 1000000)]

    (->Uptime hours minutes seconds millis)))

(defn ^Uptime get-server-uptime
  "Returns server uptime.
  Returns: time.Uptime"
  []
  (if @start-time (->> (Instant/now)
                       (Duration/between @start-time)
                       (convert-java-duration))))

(defn set-start-time!
  "Updated server start time."
  ([] (set-start-time! (Instant/now)))
  ([^Instant time] (reset! start-time time)))