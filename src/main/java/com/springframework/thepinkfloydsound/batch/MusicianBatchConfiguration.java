package com.springframework.thepinkfloydsound.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.springframework.thepinkfloydsound.entity.Musician;
import com.springframework.thepinkfloydsound.entity.Person;
import com.springframework.thepinkfloydsound.processor.PersonToMusicianProcessor;

@Configuration
@EnableBatchProcessing
public class MusicianBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public MusicianWriter musicianWriter;

	@Bean(destroyMethod = "")
	public ItemReader<Person> readerPerson(DataSource dataSource) {
		JdbcCursorItemReader<Person> cursorItemReader = new JdbcCursorItemReader<Person>();
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT * FROM people LEFT JOIN musician ON people.people_id=musician.musician_id");
		cursorItemReader.setRowMapper(new PersonRowMapper());
		return cursorItemReader;
	}
	

	public class PersonRowMapper implements RowMapper<Person> {
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person();
			person.setFirstName(rs.getString("first_name"));
			person.setLastName(rs.getString("last_name"));
			person.setAge(rs.getInt("age"));
			return person;
		}
	}

	@Bean
	public PersonToMusicianProcessor processorMusician() {
		return new PersonToMusicianProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Musician> writerMusician(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Musician>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO musician (musician_id, first_name, last_name, age, role) VALUES (:id, :firstName, :lastName, :age, :role)")
				.dataSource(dataSource).build();
	}
	
	@Bean
	public MusicianWriter musicianWriter() {
		return new MusicianWriter();
		
	}
	@Bean
	public Step stepMusician(MusicianWriter musicianWriter) {
		return stepBuilderFactory.get("stepMusician").<Person, Musician>chunk(10).reader(readerPerson(dataSource))
				.processor(processorMusician()).writer(musicianWriter).build();
	}
	
	@Bean
	public Job transformPeopleInMusician(Step stepMusician) {
		return jobBuilderFactory.get("transformPeopleInMusician").incrementer(new RunIdIncrementer()).flow(stepMusician)
				.end().build();
	}
}
