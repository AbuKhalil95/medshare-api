-- Initial values for some entities
--
INSERT INTO category (name) VALUES ('Fluid Container') ON CONFLICT DO NOTHING;
INSERT INTO category (name) VALUES ('Syringes') ON CONFLICT DO NOTHING;