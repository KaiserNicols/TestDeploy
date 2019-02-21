import { Component, OnInit } from '@angular/core';
import { Recommendation } from '../recommendation';
import { RecommendationService } from '../recommendation.service';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements OnInit {
  
  recommendations: Recommendation[];
  
  personalChartTitle = "Recommendations for you";
  personalChartType = "PieChart";
  personalChartData = [ ["Helpful", 0], ["Unhelpful", 0] ];
  personalChartColNames = ['Status', 'Count'];
  personalChartOptions = {
    'width': 400,
    'height': 300
  };

  groupChartTitle = "Recommendations for all users";
  groupChartType = "BarChart";
  groupChartData = [ ["Helpful", 0], ["Unhelpful", 0] ];
  groupChartColNames = ['Status', 'Count'];
  groupChartOptions = {
    'width': 400,
    'height': 300
  };
  
  constructor(private recommendationService: RecommendationService) {}

  ngOnInit() {
    this.getRecommendations();
    this.getAllRecommendations();
  }

  getRecommendations(): void {
    this.recommendationService.getRecommendations()
        .subscribe(recommendations => 
          {
          console.log(recommendations);
          this.recommendations = recommendations;
          this.setPersonalChartData();
          }
        );
      
  }

  getAllRecommendations(): void {
    this.recommendationService.getAllRecommendations()
        .subscribe(recommendations => 
          {
          console.log(recommendations);
          this.recommendations = recommendations
          this.setGroupChartData();
          }
        );
  }

  setPersonalChartData() : void {
    let helpfulCount = 0;
    let unhelpfulCount = 0;
    for (let i=0; i<this.recommendations.length; i++) {
      if (this.recommendations[i].helpful) {
        helpfulCount++;
      } else {
        unhelpfulCount++;
      }
    }
    this.personalChartData = [ ["Helpful", helpfulCount], ["Unhelpful", unhelpfulCount] ];
  }

  setGroupChartData() : void {
    let helpfulCount = 0;
    let unhelpfulCount = 0;
    for (let i=0; i<this.recommendations.length; i++) {
      if (this.recommendations[i].helpful) {
        helpfulCount++;
      } else {
        unhelpfulCount++;
      }
    }
    this.groupChartData = [ ["Helpful", helpfulCount], ["Unhelpful", unhelpfulCount] ];
  }
}
