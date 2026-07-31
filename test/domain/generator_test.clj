(ns domain.generator-test
  (:require [clojure.test :refer [deftest is testing]]
            [domain.generator :refer [valid-size? generate-code]]))

(deftest test-valid-size?
  (testing "Validate code size"
    (is (true? (valid-size? 5)))
    (is (true? (valid-size? 6)))
    (is (true? (valid-size? 10)))
    (is (false? (valid-size? 4)))
    (is (false? (valid-size? 11)))
    (is (false? (valid-size? "5")))
    (is (false? (valid-size? nil)))))

(deftest test-generate-code
  (testing "Generate code"
    (testing "Default size 6"
      (is (= 6 (count (generate-code)))))

    (testing "Custom size"
      (is (= 8 (count (generate-code 8))))
      (is (nil? (generate-code 4)))))

  (testing "Generated codes are strings"
    (is (string? (generate-code)))
    (is (string? (generate-code 8)))))