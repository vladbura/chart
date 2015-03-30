/*
Navicat PGSQL Data Transfer

Source Server         : POSTGRESQL ALIEN
Source Server Version : 90305
Source Host           : alienconcept.ro:5432
Source Database       : anevis
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90305
File Encoding         : 65001

Date: 2015-03-30 13:28:34
*/


-- ----------------------------
-- Sequence structure for pie_chart_id_seq
-- ----------------------------
DROP SEQUENCE "public"."pie_chart_id_seq";
CREATE SEQUENCE "public"."pie_chart_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 124
 CACHE 1;
SELECT setval('"public"."pie_chart_id_seq"', 124, true);

-- ----------------------------
-- Sequence structure for ring_chart_id_seq
-- ----------------------------
DROP SEQUENCE "public"."ring_chart_id_seq";
CREATE SEQUENCE "public"."ring_chart_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for pie_chart
-- ----------------------------
DROP TABLE IF EXISTS "public"."pie_chart";
CREATE TABLE "public"."pie_chart" (
"id" int4 DEFAULT nextval('pie_chart_id_seq'::regclass) NOT NULL,
"country" varchar(255) COLLATE "default" NOT NULL,
"weight" float4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of pie_chart
-- ----------------------------

-- ----------------------------
-- Table structure for ring_chart
-- ----------------------------
DROP TABLE IF EXISTS "public"."ring_chart";
CREATE TABLE "public"."ring_chart" (
"id" int4 DEFAULT nextval('ring_chart_id_seq'::regclass) NOT NULL,
"pie_date" varchar(255) COLLATE "default" NOT NULL,
"security" varchar(255) COLLATE "default" NOT NULL,
"weighting" float4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of ring_chart
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."pie_chart_id_seq" OWNED BY "pie_chart"."id";
ALTER SEQUENCE "public"."ring_chart_id_seq" OWNED BY "ring_chart"."id";

-- ----------------------------
-- Primary Key structure for table pie_chart
-- ----------------------------
ALTER TABLE "public"."pie_chart" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table ring_chart
-- ----------------------------
ALTER TABLE "public"."ring_chart" ADD PRIMARY KEY ("id");
