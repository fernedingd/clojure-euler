; Task: Calculate the number of letters used when writing out all numbers from 1 to 1000
(ns problems.seventeen)

(def base-numbers {1 "one"
              2 "two"
              3 "three"
              4 "four"
              5 "five"
              6 "six"
              7 "seven"
              8 "eight"
              9 "nine"
              10 "ten"
              11 "eleven"
              12 "twelve"
              13 "thirteen"
              14 "fourteen"
              15 "fifteen"
              16 "sixteen"
              17 "seventeen"
              18 "eighteen"
              19 "nineteen"
              20 "twenty"
              30 "thirty"
              40 "forty"
              50 "fifty"
              80 "eighty"})

(def extensions {10 "ty"
                 100 "hundred"
                 1000 "thousand"})

(defn first-digit
  [number]
  (loop [number number]
    (if (< number 10)
      number
      (recur (int (/ number 10))))))

(defn number-size
  [number]
  (loop [number number
         size 1]
    (let [shortened (int (/ number 10))]
      (if (zero? shortened)
        size
        (recur shortened (* size 10))))))

(defn remove-first-digit
  [number]
  (let [digit     (first-digit number)
        size      (number-size number)]
    (- number (* digit size))))

(defn needs-aggregator?
  [number]
  (if (or (<= number 100) (>= number 10000) (= number 1000))
    false
    (if (and (> number 100) (< number 1000))
      (not (zero? (mod number 100)))
      (< (remove-first-digit number) 100))))

(defn in-english
  [number]
  (loop [number number
         english ""]
    (if (zero? number)
      english
      (if (contains? base-numbers number)
        (str english (base-numbers number))
        (if (contains? base-numbers (* (int (/ number 10)) 10))
          (recur (remove-first-digit number) (str english (base-numbers (* (int (/ number 10)) 10))))
          (let [digit     (first-digit number)
                size      (number-size number)
                start     (base-numbers digit)
                extension (if (needs-aggregator? number) (str (extensions size) "and") (extensions size))]
            (recur (remove-first-digit number) (str english start extension))))))))



(comment
  (in-english 1000)

  (first-digit 100)

  (< (remove-first-digit 1000) 100)

  (- 234 (* 2 100))
  
  (number-size 1123)


  (count (->> (range 1 1001)
              (map in-english)
              (flatten)))
  :rcf)