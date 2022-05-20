interface Client {
	_id: {
		$oid: string
	};
	name: string;
	type: string;
}

interface Company {
	_id: {
		$oid: string;
	};
	name: string;
	type: string;
}

export default interface UserSchema {
	_id: {
		$oid: string;
	};
	nickname: string;
	email: string;
	pass: string;
	type: string;
	clients: Client[];
	companies: Company[];
	employee_id: string;
}