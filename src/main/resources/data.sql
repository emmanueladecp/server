/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ADMIN
 * Created: Jul 10, 2019
 */


INSERT INTO customer (id, name, phone, email) VALUES
  (1,'Dangote', '012345678','-'),
  (2,'Gates', '05465455','asdasd@sdfsd.com'),
  (3,'Alakija', '546852132','-')
;

INSERT INTO movie (id, name, date, starttime, endtime, stock)  VALUES
  (1, 'SPIDERMAN FAR FROM HOME', '2019-07-06','10:00:00','11:00:00',20),
  (2, 'SPIDERMAN FAR FROM HOME', '2019-07-06','11:30:00','12:30:00',40),
  (3, 'SPIDERMAN FAR FROM HOME', '2019-07-06','12:00:00','13:00:00',35),

  (4, 'ANABELLE COMES HOME', '2019-07-06','13:30:00','14:30:00',10),
  (5, 'ANABELLE COMES HOME', '2019-07-06','15:30:00','16:00:00',5),
  (6, 'ANABELLE COMES HOME', '2019-07-06','16:30:00','17:30:00',2),

  (7, 'SPIDERMAN FAR FROM HOME II', '2019-07-08','10:00:00','11:00:00',100),
  (8, 'SPIDERMAN FAR FROM HOME II', '2019-07-08','11:30:00','12:30:00',100),
  (9, 'SPIDERMAN FAR FROM HOME II', '2019-07-08','20:00:00','21:00:00',100),

  (10, 'ANABELLE COMES HOME II', '2019-07-20','13:30:00','14:30:00',200),
  (11, 'ANABELLE COMES HOME II', '2019-07-20','15:30:00','16:00:00',200),
  (12, 'ANABELLE COMES HOME II', '2019-07-20','19:30:00','20:30:00',200)
;


/**
INSERT INTO sales (id, customer_id, movie_id, qtybuy) VALUES
 (1, 1, 1, 2),
 (2, 1, 3, 2),
 (3, 1, 5, 2),
 (4, 2, 1, 2),
 (5, 2, 3, 2),
 (6, 2, 5, 2)
*/
