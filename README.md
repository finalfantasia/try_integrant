# A simple proof-of-concept project
  
  This is written to experiment with
 - [clojure.tools.deps](https://github.com/clojure/tools.deps.alpha)
 - [Integrant](https://github.com/weavejester/integrant)
 - [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
 - [Reloaded](https://github.com/stuartsierra/reloaded) 

#### Starting a REPL with development configuration
```bash
clj -A:dev
```

and then start the system:
```clojure
(ig-repl/go)
;; =>
;; :initiated
```

make some changes and then reset the system:
```clojure
(ig-repl/reset)
;; =>
;; :reloading (app.use-cases.date-time app.web.request-handler app.web.aleph app.services.date-time user app.main)
;; :resumed
```

and finally, halt the system:
```clojure
(ig-repl/halt)
;; =>
;; :halted
```

#### Building uberjar
```bash
./dev/build-uberjar.sh

```

#### Running uberjar

```bash
./dev/run-uberjar.sh

```

#### How to Use
```bash
curl localhost:3000
```
