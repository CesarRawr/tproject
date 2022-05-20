import { create, verify, getNumericDate } from 'https://deno.land/x/djwt@v2.4/mod.ts';
import { crypto } from "https://deno.land/std@0.139.0/crypto/mod.ts";

import type { Payload, Header } from 'https://deno.land/x/djwt@v2.4/mod.ts';

const key = await crypto.subtle.generateKey(
  { name: "HMAC", hash: "SHA-512"},
  true,
  ["sign", "verify"],
);

const header: Header = {
	alg: "HS512",
	typ: "JWT"
}

const jwtService = {
	generateToken: async (user: any): Promise<string> => {
		const payload: Payload = {
			id: user._id,
			nickname: user.nickname,
			type: user.type,
			clients: user.clients,
			companies: user.companies,
			exp: getNumericDate(60 * 60)
		}
		
		return await create(header, payload, key);
	},
	validateToken: async (token: string): Promise<Payload> => {
		return await verify(token, key);
	}
}

export default jwtService;
