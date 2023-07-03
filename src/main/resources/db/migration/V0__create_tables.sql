CREATE TABLE IF NOT EXISTS public.aircraft
(
    id     uuid PRIMARY KEY,
    model  text NOT NULL,
    status text NOT NULL
);

CREATE TABLE IF NOT EXISTS public.seat
(
    id             uuid PRIMARY KEY,
    number         text NOT NULL,
    fare_condition text NOT NULL,
    aircraft_id    uuid NOT NULL
);