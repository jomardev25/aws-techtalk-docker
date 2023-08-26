CREATE TABLE IF NOT EXISTS "users" (
	"id" BIGINT NOT NULL,
	"is_account_non_expired" BOOLEAN NULL DEFAULT NULL,
	"is_account_non_locked" BOOLEAN NULL DEFAULT NULL,
	"bio" TEXT NULL DEFAULT NULL,
	"is_credentials_non_expired" BOOLEAN NULL DEFAULT NULL,
	"email" VARCHAR(255) NOT NULL,
	"is_enabled" BOOLEAN NULL DEFAULT NULL,
	"first_name" VARCHAR(255) NOT NULL,
	"image_url" VARCHAR(255) NULL DEFAULT NULL,
	"last_name" VARCHAR(255) NOT NULL,
	"password" VARCHAR(255) NOT NULL,
	"role" VARCHAR(255) NULL DEFAULT NULL,
	"username" VARCHAR(255) NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "users_role_check" CHECK ((((role)::text = ANY ((ARRAY['AUTHOR'::character varying, 'EDITOR'::character varying, 'ADMIN'::character varying])::text[]))))
);

CREATE UNIQUE INDEX uk_6dotkott2kjsp8vw4d0m25fb7 ON users(email);
CREATE UNIQUE INDEX uk_r43af9ap4edm43mmtq01oddj6 ON users(username);

CREATE TABLE IF NOT EXISTS "tag" (
	"id" BIGINT NOT NULL,
	"name" VARCHAR(255) NOT NULL,
	PRIMARY KEY ("id")
);

CREATE UNIQUE INDEX uk_1wdpsed5kna2y38hnbgrnhi5b ON tag(name);

CREATE TABLE IF NOT EXISTS "article" (
	"id" BIGINT NOT NULL,
	"body" TEXT NULL DEFAULT NULL,
	"created_at" TIMESTAMP NULL DEFAULT NULL,
	"description" TEXT NULL DEFAULT NULL,
	"image_url" VARCHAR(255) NULL DEFAULT NULL,
	"published_at" TIMESTAMP NULL DEFAULT NULL,
	"slug" VARCHAR(255) NULL DEFAULT NULL,
	"title" VARCHAR(255) NOT NULL,
	"updated_at" TIMESTAMP NULL DEFAULT NULL,
	"author_id" BIGINT NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkmjgtny2i22jf4dqncmd436s0u" FOREIGN KEY ("author_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE UNIQUE INDEX uk_lc76j4bqg2jrk06np18eve5yj ON article(slug);

CREATE TABLE IF NOT EXISTS "article_tag" (
	"article_id" BIGINT NOT NULL,
	"tag_id" BIGINT NOT NULL,
	PRIMARY KEY ("article_id", "tag_id"),
	CONSTRAINT "fk577xeoys8nt7y5jiglowy1d59" FOREIGN KEY ("article_id") REFERENCES "tag" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "fkdxjw0bdnwduhd570fm2e92mtu" FOREIGN KEY ("tag_id") REFERENCES "article" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS "comment" (
	"id" BIGINT NOT NULL,
	"body" TEXT NULL DEFAULT NULL,
	"created_at" TIMESTAMPTZ NULL DEFAULT NULL,
	"updated_at" TIMESTAMPTZ NULL DEFAULT NULL,
	"article_id" BIGINT NOT NULL,
	"user_id" BIGINT NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fk5yx0uphgjc6ik6hb82kkw501y" FOREIGN KEY ("article_id") REFERENCES "article" ("id") ON UPDATE NO ACTION ON DELETE CASCADE,
	CONSTRAINT "fkqm52p1v3o13hy268he0wcngr5" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS "token" (
	"id" INTEGER NOT NULL,
	"expired" BOOLEAN NOT NULL,
	"revoked" BOOLEAN NOT NULL,
	"token" VARCHAR(255) NULL DEFAULT NULL,
	"token_type" VARCHAR(255) NULL DEFAULT NULL,
	"user_id" BIGINT NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "fkj8rfw4x0wjjyibfqq566j4qng" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "token_token_type_check" CHECK ((((token_type)::text = 'BEARER'::text)))
);

CREATE UNIQUE INDEX uk_pddrhgwxnms2aceeku9s2ewy5 ON token(token);

CREATE TABLE IF NOT EXISTS "user_following" (
	"follower_id" BIGINT NOT NULL,
	"followee_id" BIGINT NOT NULL,
	PRIMARY KEY ("follower_id", "followee_id"),
	CONSTRAINT "fk24k9blpfqpm29xohut39du4dn" FOREIGN KEY ("followee_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT "fkbhwj1yevud330mbllu4dt7gn7" FOREIGN KEY ("follower_id") REFERENCES "users" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION
);

INSERT INTO "users" ("id", "is_account_non_expired", "is_account_non_locked", "bio", "is_credentials_non_expired", "email", "is_enabled", "first_name", "image_url", "last_name", "password", "role", "username") VALUES
	(1, 'true', 'true', 'This is my biography', 'true', 'john@local.com', 'true', 'John', 'C:\', 'Doe', '$2a$10$TT2T4/.4wfV3XI53s5qSKerrlqJxM6hpGkz3CzWGgU2sO6r1PFAFK', 'AUTHOR', 'john'),
	(3, 'true', 'true', 'This is my biography', 'true', 'admin@local.com', 'true', 'John', 'C:\', 'Doe', '$2a$10$XzdpqpVk.IkHF6Cj.uo3puFaP/1sXsQECXhmzOTu53hZetKNPwUqS', 'AUTHOR', 'admin');

INSERT INTO "tag" ("id", "name") VALUES
	(102, 'Vacation'),
	(103, 'Summer'),
	(152, 'Finibus'),
	(153, 'Bonorum'),
	(154, 'Standard'),
	(155, 'Ipsum');
	
INSERT INTO "article" ("id", "body", "created_at", "description", "image_url", "published_at", "slug", "title", "updated_at", "author_id") VALUES
	(152, 'All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined withAll the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with.', '2023-08-21 01:36:26.414632', 'Perfect Summer Vacation', 'perfe1.jpg', '2023-08-27 02:46:01', 'perfect-summer', 'Some Tips for Planning a Perfect Summer Vacation', '2023-08-21 01:36:26.414632', 1),
	(203, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum', '2023-08-21 03:01:44.453932', 'The standard Lorem Ipsum passage', 'perfect4.png', '2023-08-27 02:46:01', 'The-standard -Lorem-Ipsum-passage', 'The standard Lorem Ipsum passage, used since the 1500s', '2023-08-21 03:01:44.453932', 1),
	(202, 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.', '2023-08-21 03:00:14.286614', 'Finibus Bonorum et Malorum', 'perfect3.jpg', '2023-08-27 02:46:01', 'Finibus-Bonorum', 'Section 1.10.32 of de Finibus Bonorum et Malorum, written by Cicero in 45 BC', '2023-08-21 03:00:14.286614', 1),
	(52, 'Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance.', '2023-08-16 19:30:09.151725', 'Ever wonder how?', 'perfect2.jpg', '2023-08-27 02:46:01', 'how-to-train-your-dragon', 'Contrary to popular belief, Lorem Ipsum is not simply random text.', '2023-08-16 19:30:09.151725', 1),
	(354, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum', '2023-08-27 00:57:32.985463', 'The standard Lorem Ipsum passage', 'perfect4.png', '2023-08-27 02:46:01', 'test-2', 'The standard Lorem Ipsum passage, used since the 1500s', '2023-08-27 00:57:32.985463', 3),
	(355, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum', '2023-08-27 00:58:26.733378', 'The standard Lorem Ipsum passage', 'perfect4.png', '2023-08-27 02:46:01', 'test', 'Test', '2023-08-27 00:58:26.733378', 3),
	(353, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum', '2023-08-27 00:57:07.284433', 'The standard Lorem Ipsum passage', 'perfect4.png', '2023-08-27 02:46:01', 'The standard Lorem Ipsum passage', 'The standard Lorem Ipsum passage, used since the 1500s', '2023-08-27 00:57:07.284433', 3),
	(402, 'Nibh cras pulvinar mattis nunc sed. Ipsum dolor sit amet consectetur adipiscing elit. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Faucibus a pellentesque sit amet porttitor eget dolor morbi. Non nisi est sit amet facilisis magna. Arcu risus quis varius quam quisque id. Fermentum odio eu feugiat pretium nibh ipsum. Augue ut lectus arcu bibendum at varius. Ac turpis egestas maecenas pharetra convallis posuere morbi leo. Quis vel eros donec ac. Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum posuere. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus semper. Lacus sed viverra tellus in hac habitasse platea dictumst. Vulputate ut pharetra sit amet aliquam id diam maecenas ultricies.', '2023-08-27 01:30:40.763809', 'Lorem ipsum is placeholder text commonly used in the graphic, print, and publishing industries for previewing layouts and visual mockups.', 'perfect4.png', '2023-08-27 02:46:01', 'lorem-ipsum-placehoder', 'Lorem ipsum is placeholder text commonly used in the graphic', '2023-08-27 01:30:40.763809', 3),
	(452, 'Aliquam nulla facilisi cras fermentum odio eu. Amet porttitor eget dolor morbi non. Feugiat in fermentum posuere urna nec. Commodo quis imperdiet massa tincidunt. Sed ullamcorper morbi tincidunt ornare massa eget egestas purus. A cras semper auctor neque vitae tempus quam pellentesque. Purus ut faucibus pulvinar elementum integer enim neque volutpat. Duis ultricies lacus sed turpis. Non pulvinar neque laoreet suspendisse interdum consectetur libero id. Amet consectetur adipiscing elit ut aliquam purus sit. Risus feugiat in ante metus dictum at tempor. Dictum fusce ut placerat orci nulla. Fringilla phasellus faucibus scelerisque eleifend donec pretium. Mi bibendum neque egestas congue quisque egestas diam in.', '2023-08-27 01:46:11.116708', 'Aliquam nulla facilisi cras fermentum odio eu. Amet porttitor eget dolor morbi non. Feugiat in fermentum posuere urna nec. Commodo quis imperdiet massa tincidunt. Sed ullamcorper morbi tincidunt ornare massa eget egestas purus.', 'perfect3.jpg', '2023-08-27 02:46:01', 'Aliquam', 'Aliquam nulla facilisi cras fermentum odio eu', '2023-08-27 01:46:11.116708', 3),
	(453, 'Nibh cras pulvinar mattis nunc sed. Ipsum dolor sit amet consectetur adipiscing elit. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Faucibus a pellentesque sit amet porttitor eget dolor morbi. Non nisi est sit amet facilisis magna. Arcu risus quis varius quam quisque id. Fermentum odio eu feugiat pretium nibh ipsum. Augue ut lectus arcu bibendum at varius. Ac turpis egestas maecenas pharetra convallis posuere morbi leo. Quis vel eros donec ac. Elementum pulvinar etiam non quam lacus suspendisse faucibus interdum posuere. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus semper. Lacus sed viverra tellus in hac habitasse platea dictumst. Vulputate ut pharetra sit amet aliquam id diam maecenas ultricies.', '2023-08-27 01:49:33.423375', 'Nibh cras pulvinar mattis nunc sed. Ipsum dolor sit amet consectetur adipiscing elit. Nisi quis eleifend quam adipiscing vitae proin sagittis nisl. Faucibus a pellentesque sit amet porttitor eget dolor morbi. Non nisi est sit amet facilisis magna.', 'perfect2.jpg', '2023-08-27 02:46:01', 'lorem-ipsum-is-pulvinar ', 'Nibh cras pulvinar mattis nunc sed. Ipsum dolor sit amet consectetur adipiscing elit', '2023-08-27 01:49:33.423375', 3),
	(502, 'Tortor id aliquet lectus proin nibh nisl. Platea dictumst vestibulum rhoncus est. Vulputate mi sit amet mauris commodo quis imperdiet massa tincidunt. Non odio euismod lacinia at quis risus sed. Faucibus turpis in eu mi bibendum. Enim ut tellus elementum sagittis vitae et. Blandit libero volutpat sed cras ornare arcu. Hac habitasse platea dictumst vestibulum rhoncus est pellentesque elit. Interdum velit laoreet id donec ultrices tincidunt. Scelerisque eu ultrices vitae auctor eu augue ut lectus arcu. Egestas tellus rutrum tellus pellentesque. Cursus eget nunc scelerisque viverra mauris. Justo nec ultrices dui sapien eget. Ultrices sagittis orci a scelerisque purus semper eget duis. Malesuada fames ac turpis egestas maecenas. Sit amet mauris commodo quis imperdiet massa tincidunt nunc. Vulputate eu scelerisque felis imperdiet proin. Pellentesque nec nam aliquam sem. Eget felis eget nunc lobortis. Massa tempor nec feugiat nisl pretium fusce id velit.', '2023-08-27 01:55:44.786843', 'Tortor id aliquet lectus proin nibh nisl. Platea dictumst vestibulum rhoncus est. Vulputate mi sit amet mauris commodo quis imperdiet massa tincidunt. Non odio euismod lacinia at quis risus sed.n eu mi bibendum. Enim ut tellus elementum sagittis vitae et. Blandit libero volutpat sed cras ornare arcu. Hac habitasse platea dictumst vestibulum rhoncus est pellentesque elit.', 'perfect4.png', '2023-08-27 02:46:01', 'tortor- id-aliquet', 'Tortor id aliquet lectus proin nibh nisl.', '2023-08-27 01:55:44.786843', 3);
