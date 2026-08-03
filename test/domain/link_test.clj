(ns domain.link-test
  (:require [clojure.test :refer [deftest is testing]]
            [domain.link :refer [valid-url? create-link-record!]]
            [core.memory :refer [links code-occupied?]]))

(defn clean-links []
  (reset! links {}))

(deftest test-valid-url?
  (testing "Validate URL"
    (is (true? (valid-url? "https://google.com")))
    (is (false? (valid-url? "")))
    (is (false? (valid-url? nil)))))

(deftest test-create-link-record!
  (testing "Create link record with generated code"
    (clean-links)
    (let [result (create-link-record! "https://google.com")]
      (is (contains? result :code))
      (is (contains? result :url))
      (is (= "https://google.com" (:url result)))
      (is (string? (:code result)))
      (is (= 6 (count (:code result))))))

  (testing "Create link record with specified code"
    (clean-links)
    (let [result (create-link-record! "https://google.com" "abc123")]
      (is (= {:url "https://google.com" :code "abc123"} result))
      (is (true? (code-occupied? "abc123")))))

  (testing "Create link record with duplicate code"
    (clean-links)
    (create-link-record! "https://google.com" "abc123")
    (let [result (create-link-record! "https://github.com" "abc123")]
      (is (not= "abc123" (:code result)))  ; должен сгенерировать новый
      (is (string? (:code result)))
      (is (= "https://github.com" (:url result)))))

  (testing "Create link record with invalid URL"
    (clean-links)
    (is (= {:error "Invalid URL"} (create-link-record! "")))
    (is (empty? @links))))
