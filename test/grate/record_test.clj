(ns grate.record-test
  (:require [clojure.test :refer :all]
            [grate.record :refer :all]))

(deftest test-parse-record-bar
  (testing "Parse string of fields separated by a bar"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (parse " Crone |  Todd| M |purple  |   1970-10-10   ")))))

(deftest test-parse-record-csv
  (testing "Parse string of fields separated by a comma"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (parse " Crone,  Todd, M ,purple  ,   1970-10-10   ")))))

(deftest test-parse-record-whitespace
  (testing "Parse string of fields separated by a space"
    (is (= {:last-name      "Crone"
            :first-name     "Todd"
            :gender         "M"
            :favorite-color "purple"
            :date-of-birth  "1970-10-10"}
           (parse " Crone   Todd M purple     1970-10-10   ")))))

(deftest test-parse-partial-record
  (testing "Parse string of fields separated by a bar missing some columns"
    (is (nil? (parse " Crone |  Todd| M |  ")))))

(deftest test-no-record
  (testing "Parse string of fields separated by a bar"
    (is (nil? (parse "")))))

(deftest test-converting-record-date-to-json-str
  (testing "Write record out as JSON"
    (is (= "{\"last-name\":\"Crone\",\"first-name\":\"Todd\",\"gender\":\"M\",\"favorite-color\":\"hazel\",\"date-of-birth\":\"1970-10-10\"}"
           (to-json {:last-name      "Crone"
                     :first-name     "Todd"
                     :gender         "M"
                     :favorite-color "hazel"
                     :date-of-birth  "1970-10-10"})))))