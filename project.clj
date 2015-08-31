(defproject bago2k4/cljs-dynamic-resources "0.0.1"
  :description "Dynamic load javascript and style"
  :url "https://github.com/bago2k4/cljs-dynamic-resources"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [
    [org.clojure/clojure "1.7.0"] ; Lisp on the JVM http://clojure.org/documentation
    [org.clojure/clojurescript "1.7.107"] ; ClojureScript compiler https://github.com/clojure/clojurescript
    [prismatic/dommy "1.1.0"] ; DOM manipulation and event library https://github.com/Prismatic/dommy
  ]

  :plugins [
    [lein-cljsbuild "1.0.5"] ; ClojureScript compiler https://github.com/emezeske/lein-cljsbuild
    [lein-doo "0.1.4"] ; A plugin to run tests in many JS environments https://github.com/bensu/doo]
  ]

  :cljsbuild {
    :builds {
      :test {
        :id "test"
        :source-paths ["src" "test"]
        :compiler {
          :main "test.test"
          :output-to "target/unit-test.js"
          :optimizations :none
          :pretty-print true}}}}

  :aliases {
    "test!" ["doo" "phantom" "test" "once"]})