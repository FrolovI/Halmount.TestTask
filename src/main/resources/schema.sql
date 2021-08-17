CREATE TABLE Bank (
  idBank       INT           IDENTITY PRIMARY KEY
);
CREATE TABLE Client (
  idClient     INT            IDENTITY PRIMARY KEY,
  idBank       INT            NOT NULL,
  fullName     VARCHAR(255)   NOT NULL,
  phoneNumber  VARCHAR(255)   NOT NULL,
  email        VARCHAR(255)   NOT NULL,
  passNumber   VARCHAR(255)   NOT NULL,
  FOREIGN KEY(IDBANK) REFERENCES Bank(idBank)
);
CREATE TABLE Credit (
  idCredit      INT            IDENTITY PRIMARY KEY,
  idBank        INT            NOT NULL,
  creditLimit   DECIMAL(18,2)  NOT NULL,
  percentCredit DECIMAL(7,5)   NOT NULL,
  FOREIGN KEY(IDBANK) REFERENCES Bank(idBank)
);
CREATE TABLE CreditOffer (
  idCreditOffer  INT            IDENTITY PRIMARY KEY,
  idClient       INT            NOT NULL,
  idCredit       INT            NOT NULL,
  sumCredit      DECIMAL(18,2)  NOT NULL,
  FOREIGN KEY(IDCLIENT)  REFERENCES Client(idClient),
  FOREIGN KEY(IDCREDIT)  REFERENCES Credit(idCredit)
);
CREATE TABLE Payment (
  idPayment     INT            IDENTITY PRIMARY KEY,
  idCreditOffer INT            NOT NULL,
  payDate       DATE           NOT NULL,
  sumPay        DECIMAL(18,2)  NOT NULL,
  sumPayCredit  DECIMAL(18,2)  NOT NULL,
  sumPercent    DECIMAL(18,2)  NOT NULL,
  FOREIGN KEY(IDCREDITOFFER) REFERENCES CreditOffer(idCreditOffer)
);