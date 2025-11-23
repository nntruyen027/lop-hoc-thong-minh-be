CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    file_name TEXT,
    stored_name TEXT,
    url TEXT,
    content_type VARCHAR(255),
    size BIGINT,
    uploaded_at TIMESTAMP
);
