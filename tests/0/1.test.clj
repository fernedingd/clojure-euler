(require '[clojure.test :refer [are deftest is testing]])

(require '[problems.0.1 :as problem-one])

(deftest multiple?-test
  (testing "Should return true for same value"
    (is (problem-one/multiple? 1 1))
    (is (problem-one/multiple? 2 2))
    (is (problem-one/multiple? 123 123)))
  (testing "Should return true for multiples"
    (is (problem-one/multiple? 2 4))
    (is (problem-one/multiple? 23 (* 23 12))))
  (testing "Should return false for smaller numbers"
    (is (not (problem-one/multiple? 3 0)))
    (is (not (problem-one/multiple? 10 2))))
  (testing "Should return false for non-multiples"
    (is (not (problem-one/multiple? 5 9)))
    (is (not (problem-one/multiple? 3 5)))))