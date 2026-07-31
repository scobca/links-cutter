(ns memory-test
  (:require [clojure.test :refer [deftest is testing]]
            [memory :refer :all]))

(defn clean-links
  "Clean links memory before each test."
  []
  (reset! links {}))

(deftest test-code-occupied?
  (testing "Check if code is occupied"
    (clean-links)
    (is (false? (code-occupied? "abc123")))
    (save-link-record! "abc123" "https://google.com")
    (is (true? (code-occupied? "abc123")))))

(deftest test-find-link-record
  (testing "Try to find link record into memory"
    (clean-links)
    (is (nil? (find-link-record "abc123")))
    (save-link-record! "abc123" "https://google.com")
    (is (=
         {:code "abc123" :url "https://google.com"}
         (find-link-record "abc123")))))

(deftest test-remove-link-record!
  (testing "Remove link record"
    (clean-links)
    (save-link-record! "abc123" "https://google.com")
    (remove-link-record! "abc123")
    (is (false? (code-occupied? "abc123")))
    (is (empty? @links))))

(deftest test-save-link-record!
  (testing "Save link record"
    (clean-links)
    (save-link-record! "abc123" "https://google.com")
    (is (code-occupied? "abc123"))
    (is (= "https://google.com" (get @links "abc123")))
    (is (= {"abc123" "https://google.com"} @links))))

(deftest test-show-links
  (testing "Show links"
    (clean-links)
    (save-link-record! "abc123" "https://google.com")
    (save-link-record! "def456" "https://github.com")
    (save-link-record! "ghi789" "https://clojure.org")

    (testing "Show all links"
      (is (= {"abc123" "https://google.com"
              "def456" "https://github.com"
              "ghi789" "https://clojure.org"}
             (show-links))))

    (testing "Show last N links"
      (is (= {"def456" "https://github.com"
              "ghi789" "https://clojure.org"}
             (show-links 2))))))

