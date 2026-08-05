(ns core.time_test
  (:require [clojure.test :refer :all]
            [core.time :as sut]
            [dto.time])
  (:import [java.time Duration Instant]))

(defn clean-time
  "Clean atom time counter for each test."
  []
  (reset! sut/start-time nil))

(deftest test-convert-java-duration
  (testing "Convert Duration to custom Clojure map"

    (testing "Zero duration"
      (let [duration (Duration/ofSeconds 0)]
        (is (= (dto.time/->Uptime 0 0 0 0)
               (#'sut/convert-java-duration duration)))))

    (testing "Seconds only"
      (let [duration (Duration/ofSeconds 45)]
        (is (= (dto.time/->Uptime 0 0 45 0)
               (#'sut/convert-java-duration duration)))))

    (testing "Minutes and seconds"
      (let [duration (Duration/ofSeconds 125)]  ; 2 минуты 5 секунд
        (is (= (dto.time/->Uptime 0 2 5 0)
               (#'sut/convert-java-duration duration)))))

    (testing "Hours, minutes and seconds"
      (let [duration (Duration/ofSeconds 3725)]  ; 1 час 2 минуты 5 секунд
        (is (= (dto.time/->Uptime 1 2 5 0)
               (#'sut/convert-java-duration duration)))))

    (testing "With milliseconds"
      (let [duration (Duration/ofMillis 1500)]  ; 1 секунда 500 миллисекунд
        (is (= (dto.time/->Uptime 0 0 1 500)
               (#'sut/convert-java-duration duration)))))

    (testing "Complex duration with hours, minutes, seconds and milliseconds"
      (let [duration (Duration/ofMillis 3725500)]  ; 1 час 2 минуты 5 секунд 500 мс
        (is (= (dto.time/->Uptime 1 2 5 500)
               (#'sut/convert-java-duration duration)))))

    (testing "Large duration"
      (let [duration (Duration/ofDays 2)]  ; 2 дня = 48 часов
        (is (= (dto.time/->Uptime 48 0 0 0)
               (#'sut/convert-java-duration duration)))))))

(deftest test-get-server-uptime
  (testing "Get server uptime"

    (testing "Returns nil when start-time is not set"
      (clean-time)
      (is (nil? (sut/get-server-uptime))))

    (testing "Returns correct uptime after setting start-time"
      (let [start (Instant/now)]

        (sut/set-start-time! start)
        (Thread/sleep 100)

        (let [uptime (sut/get-server-uptime)]
          (is (map? uptime))

          (is (>= (:seconds uptime) 0))
          (is (>= (:millis uptime) 0))

          (is (>= (:hours uptime) 0))
          (is (>= (:minutes uptime) 0)))))))

(deftest test-set-start-time
  (testing "Set start time"

    (testing "Sets start-time with explicit Instant"
      (clean-time)
      (let [expected-time (Instant/parse "2024-01-01T00:00:00Z")]
        (sut/set-start-time! expected-time)
        (is (= expected-time @sut/start-time))))

    (testing "Sets start-time with current time when no argument provided"
      (clean-time)
      (let [before (Instant/now)]
        (Thread/sleep 10)
        (sut/set-start-time!)

        (let [actual @sut/start-time]
          (is (inst? actual))
          (is (not (nil? actual)))
          (is (pos? (.compareTo actual before))))))))