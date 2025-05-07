package com.emt.emtlabs.dto;

import java.util.Date;

public record authLogsDto (String username, String token, Date issuedAt, Date expiresAt) {
}
