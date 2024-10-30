CREATE TABLE IF NOT EXISTS swallow (
    id TEXT NOT NULL,
    index_number INTEGER NOT NULL ,
    date TEXT NOT NULL ,
    longitude REAL NOT NULL ,
    latitude REAL NOT NULL ,
    longitude_difference REAL NOT NULL ,
    latitude_difference REAL NOT NULL ,
    distance REAL NOT NULL,
    table_number TEXT NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (table_number) REFERENCES swallowtable(id)
);

CREATE TABLE IF NOT EXISTS  swallowtable(
    id TEXT NOT NULL ,
    date TEXT NOT NULL ,
    name TEXT NOT NULL ,
    PRIMARY KEY (id)
);

