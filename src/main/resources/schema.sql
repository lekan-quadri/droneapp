CREATE TABLE drone (
  id INTEGER NOT NULL AUTO_INCREMENT,
  serialNumber varchar(100),
  model varchar(20) NOT NULL,
  weightLimit float NOT NULL,
  batteryCapacity float NOT NULL,
  state varchar(20) NOT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE medication (
  id INTEGER NOT NULL AUTO_INCREMENT,
  name varchar(150),
  weight float NOT NULL,
  code varchar(30) NOT NULL,
  image binary NULL,
  PRIMARY KEY (id)
);

CREATE TABLE cargo(
    drone_id INTEGER,
    medication_id INTEGER,
    FOREIGN KEY (drone_id) REFERENCES drone(id),
    FOREIGN KEY (medication_id) REFERENCES medication(id)
);