; Task: Find the sum of all digits of 2^1000
(ns problems.10.16)

(defn digits
  [number]
  (loop [number-string (str (biginteger number))
         digits []]
    (let [digit (first number-string)]
     (if (nil? digit)
       digits
       (let [digit (- (byte digit) 48)]
         (recur (rest number-string) (conj digits digit)))))))

(defn sum-digits
  [number]
  (reduce + (digits number)))

(comment
  (digits 123)
  (digits (.pow (biginteger 2) 1000))
  (sum-digits (.pow (biginteger 2) 1000))
  :rcf)