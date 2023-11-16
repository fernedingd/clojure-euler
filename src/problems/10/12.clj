; Task: Calculate the first triangle number that has over fivehoundred divisors
(ns problems.10.12)

(defn dividable?
  [num divisor]
  (= 0 (mod num divisor)))

(defn triangle-numbers
  "Returns a set of numbers up to a given limit where n = 1 + 2 + 3 + ..."
  [limit]
  (loop [number  1
         numbers []]
    (let [result (reduce + (range (inc number)))] 
      (if (< result limit)
        (recur (inc number) (conj numbers result))
        numbers))))

(defn sieve
  "Returns all primes in the given range"
  [numbers]
  (loop [numbers numbers
         primes  []]
    (let [prime (first numbers)]
      (if (nil? prime)
        primes
        (let [remaining (filter #(not (dividable? % prime)) (rest numbers))]
          (recur remaining (conj primes prime)))))))

(defn filter-primes
  "Returns all primes from a list of numbers by comparing them with a list of known primes.
   The highest number can't be significantly higher than the highest prime squared."
  [numbers primes]
  (loop [numbers numbers
         primes primes]
    (let [prime (first primes)]
      (if (nil? prime)
        numbers
        (let [remaining (filter #(not (dividable? % prime)) numbers)]
          (recur remaining (rest primes)))))))

(defn primes
  "Returns a list of all primes up to a given limit"
  [limit]
  (let [split        (int (if (< (Math/sqrt limit) 2) 2 (Math/sqrt limit)))
        small-primes (sieve (range 2 (inc split)))
        high-primes  (filter-primes (range split limit) small-primes)]
    (concat small-primes high-primes)))

(defn prime-factors
  "Returns the prime factors of the given number"
  [number]
  (let [primes (set (primes (Math/sqrt number)))]
   (loop [number number
          prime-factors []]
     (if (contains? primes number)
       (conj prime-factors number)
       (let [next-prime-factor (loop [primes primes]
                                 (let [prime (first primes)] 
                                   (if (nil? prime)
                                     nil
                                     (if (dividable? number prime)
                                       prime
                                       (recur (rest primes))))))]
         (if (nil? next-prime-factor)
           prime-factors
           (recur (/ number next-prime-factor) (conj prime-factors next-prime-factor))))))))

(defn divisors-count
  [number]
  (reduce * (map inc (vals (frequencies (prime-factors number))))))

(comment
  (first (filter #(> (divisors-count %) 500) (triangle-numbers 100000000)))

  (prime-factors 3)

  (primes 3)
  :rcf)