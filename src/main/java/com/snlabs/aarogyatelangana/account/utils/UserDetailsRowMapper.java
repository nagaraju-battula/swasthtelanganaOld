/**
 * 
 */
package com.snlabs.aarogyatelangana.account.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.snlabs.aarogyatelangana.account.beans.UserDetails;

/**
 * @author nbattula
 *
 */
public class UserDetailsRowMapper implements RowMapper
{
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDetails userDetails = new UserDetails();
		
		userDetails.setLoginId(rs.getString("F_LOGIN_ID"));
		userDetails.setMobileNumber(rs.getString("F_MOBILE_NUMBER"));
		userDetails.setDisplayName(rs.getString("F_DISPLAY_NAME"));
		userDetails.setUserRole(rs.getString("F_ROLE"));
		return userDetails;
	}
}