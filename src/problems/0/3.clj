;; Task: Find the largest prime factor of 600851475143
(ns problems.0.3)

(defn prime? 
  [num] 
  (and (>= num 2)
       (empty? (->> (range 2 num)
                    (filter #(= 0 (mod num %)))))))

(defn prime-factor? 
  [factor, num] 
  (and (< 1 factor) 
       (prime? factor) 
       (= 0 (mod num factor))))
  
(defn next-prime 
  [num] 
  (if (prime? num)
    num
    (next-prime (+ 1 num))))
  
(defn next-prime-factor 
  [factor num] 
  (if (<= factor num) 
    (if (prime-factor? factor num) 
      factor 
      (next-prime-factor (next-prime (+ 1 factor)) num))
    nil)) 

(defn get-prime-factors
  [number]
  (loop [num number, factors []]
    (let [factor (next-prime-factor 0 num)]
      (if (prime? (/ num factor)) (conj factors factor (/ num factor))
          (recur (/ num factor) (conj factors factor))))))
  
(comment
  (def target 600851475143)
  
  (get-prime-factors target)
  :rcf)