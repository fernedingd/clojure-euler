; Task: Find the pythagorean triples where a + b + c = 1000
(ns problems.0.9)

(defn square 
  [value]
  (* value value))

(defn pythagorean?
  [[a b c]]
  (and (< a b)
       (< b c)
       (= (+ (square a) (square b)) (square c))))

(defn get-triples
  [sum]
  (loop [low     1
         middle  2
         high    (- sum 3)
         triples []]
    (if (> (- high 1) (+ middle 1))
      (recur low (+ middle 1) (- high 1) (conj triples [low middle high]))
      (if (and (< (+ low 1) middle) (< (+ low 2) middle) (> (- sum (+ low 1) (+ low 2)) middle))
        (recur (+ low 1) (+ low 2) (- sum (+ low 1) (+ low 2)) (conj triples [low middle high]))
        (conj triples [low middle high])))))

(defn triple-product
  [[a b c]]
  (* a b c))

(comment
  (pythagorean? [5 12 13])
  (->> (get-triples 30)
       (filter pythagorean?))
  
  (->> (get-triples 1000)
       (filter pythagorean?)
       (map triple-product))
  :rcf)